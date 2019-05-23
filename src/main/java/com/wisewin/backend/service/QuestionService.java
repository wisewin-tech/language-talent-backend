package com.wisewin.backend.service;

import com.wisewin.backend.dao.QuestionDAO;
import com.wisewin.backend.entity.bo.AnsDesBO;
import com.wisewin.backend.entity.bo.ChapterIdBO;
import com.wisewin.backend.entity.bo.QuestionBO;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("questionService")
@Transactional
public class QuestionService {
    @Resource
    private QuestionDAO questionDAO;
    @Resource
    private LanguageService  languageService;
    @Resource
    private CourseService  courseService;
    @Resource
    private LevelService  levelService;
    @Resource
    private ChapterService  chapterService;
    /**
     * 添加题目到题库
     * @param questionBO
     * @return
     */
    public Integer addquestion(QuestionBO questionBO){
        return questionDAO.addquestion(questionBO);
    }

    /**
     * 删除测试题库
     */
    public void deleteTest(Integer userId){
       questionDAO.deleteTest(userId);
    }

    /**
     * 查询此次添加数据
     */
    public List<QuestionBO> queryTest(Integer userId){
        return questionDAO.queryTest(userId);
    }

    /**
     * 添加测试题库
     *
     */

    public Integer addquestionTest(QuestionBO questionBO){
        return questionDAO.addquestionTest(questionBO);
    }
    /**
     * 查询题库
     * @param map
     * @return
     */
    public List<QuestionBO> selectQuestion(Map<String,Object> map){
        return questionDAO.selectQuestion(map);
    }
    /**
     * 查询总条数
     * @param mapParam
     * @return
     */
    public Integer selectbylimitCount(Map<String,Object> mapParam){
        return questionDAO.selectbylimitCount(mapParam);
    }

    /**
     * 修改题目
     * @param questionBO
     * @return
     */
    public boolean updateQuestion(QuestionBO questionBO){
        return questionDAO.updateQuestion(questionBO);
    }

    /**
     * 查询某题的所有信息
     * @param id
     * @return
     */
    public QuestionBO getQuestion(Integer id){
        return questionDAO.getQuestion(id);
    }
    //通过课程id查找语言id
    public ChapterIdBO getCourseId(Integer id){
        return questionDAO.getCourseId(id);
    }
    //通过课时id查找课程id级别id语言id
    public ChapterIdBO getChapterId(Integer id){
        return questionDAO.getChapterId(id);
    }

    /**
     * 删除题目
     * @param idArr
     * @return
     */
    public Integer delQuestion(Integer []idArr){
        return questionDAO.delQuestion(idArr);
    }

    /**
     * Excel 导入试题
     * @param file
     */
    public Integer importQuestions(MultipartFile file,Integer userId) {
        int rows=0;
        try {
            Workbook wb=new HSSFWorkbook(file.getInputStream());
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            for (int i=1;i<=sheet.getFirstRowNum();i++) {
                rows++;
                Row row = sheet.getRow(i);// 每一行
                QuestionBO  questionBO=new QuestionBO();
                questionBO.setQuestionType(this.analysisType(row.getCell(0))); //题类型
                questionBO.setTestType(this.analysisStage(row.getCell(1))); //阶段
                questionBO.setScore(this.analysisAnswer(row.getCell(2))); //分值解析
                String[]  answer=this.analysisContent(row.getCell(11),row.getCell(12)
                        ,row.getCell(13),row.getCell(14),row.getCell(15),row.getCell(16),row.getCell(17),
                        row.getCell(18),row.getCell(19),row.getCell(20),row.getCell(21),row.getCell(22),
                        row.getCell(23),row.getCell(24),row.getCell(25),row.getCell(4),row.getCell(3),questionBO.getTestType());
                questionBO.setAnswer(answer[1]); //答案 和解析
                questionBO.setOption(answer[0]);//选项
                questionBO.setTopic(this.getTopic(row.getCell(6),row.getCell(7),row.getCell(8),row.getCell(9),row.getCell(10)));
                questionBO.setRelevanceId(this.getCoordinate(row.getCell(5),questionBO.getQuestionType()));
                questionBO.setCreateTime(new Date());
                questionBO.setCreateUserId(userId);

                questionDAO.addquestionTest(questionBO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  rows;
        }
        return  null;
    }


    //解析类型
    private String analysisType(Cell cell)throws Exception{
        String type = cell.getStringCellValue().trim();
        if("常规".equals(type)){
            return "common";
        }else if("判断".equals(type)){
            return "judge";
        }else if("翻译".equals(type)){
            return "translate";
        }else if("拼写".equals(type)){
            return "write";
        }else if("听力和文本匹配".equals(type)){
            return "hearingAndTest";
        }else if("听音完成句子".equals(type)){
            return "hearingAndSentence";
        }else if("图文匹配".equals(type)){
            return "imageText";
        }else if("阅读".equals(type)){
            return "read";
        }else{
           throw new RuntimeException();
        }
    }


    //阶段解析
    private String analysisStage(Cell cell)throws Exception{
        String stage = cell.getStringCellValue().trim();
        if("语言能力测试".equals(stage)){
            return "languageTest";
        }else if("学前热身".equals(stage)){
            return "warmUp";
        }else if("随堂测试".equals(stage)){
            return "test";
        }else if("课程考证".equals(stage)){
            return "courseCertificate";
        }else{
            throw new RuntimeException();
        }
    }


    //分值解析
    private String analysisAnswer(Cell cell)throws Exception{
        String stage = cell.getStringCellValue().trim();
        List<Integer>  list=new ArrayList();
        String[] split = stage.split("\\)");
        for (String str:split) {
          String[]   scores = str.trim().split("\\(");
          for(String score:scores){
              if(score!=null && score.length()>0){
                  list.add(Integer.parseInt(score));
              }
          }
        }
        return  JSONArray.fromObject(list).toString();
    }



    //选项 & 答案 & 解析    解析
    private String[] analysisContent(Cell option1,Cell option2,Cell option3,Cell option4,Cell option5,Cell option6,Cell option7,Cell option8,Cell option9,Cell option10,
                                     Cell option11,Cell option12,Cell option13,Cell option14,Cell option15,Cell answers,Cell analysiss,String type)throws Exception{
        //选项
        List<List<String>>  option=new ArrayList<>();

        List<String> opt1= this.getlistStrings(option1);
        if(opt1!=null){
            option.add(opt1);
        }
        List<String> opt2= this.getlistStrings(option2);
        if(opt2!=null){
            option.add(opt2);
        }
        List<String> opt3= this.getlistStrings(option3);
        if(opt3!=null){
            option.add(opt3);
        }
        List<String> opt4= this.getlistStrings(option4);
        if(opt4!=null){
            option.add(opt4);
        }
        List<String> opt5= this.getlistStrings(option5);
        if(opt5!=null){
            option.add(opt5);
        }
        List<String> opt6= this.getlistStrings(option6);
        if(opt6!=null){
            option.add(opt6);
        }
        List<String> opt7= this.getlistStrings(option7);
        if(opt7!=null){
            option.add(opt7);
        }
        List<String> opt8= this.getlistStrings(option8);
        if(opt8!=null){
            option.add(opt8);
        }
        //答案
        List<String> answer = this.getlistStrings(answers);
        //解析
        List<String> analysis = this.getlistStrings(analysiss);

        List<AnsDesBO>   des=new ArrayList<>();

        for(int i=0;i<option.size();i++){
            List<String>  opt=option.get(i);
            AnsDesBO  ans=new AnsDesBO();
            for(int x=0;x<opt.size();x++){
                for(int y=0;y<answer.size();y++){
                    String answe=answer.get(y);
                    String subStr = opt.get(x).substring(0, 1);
                    if(answe.equalsIgnoreCase(subStr)){
                        ans.setAns(x);
                        ans.setDes(analysis.get(x));

                    }
                }
            }
            des.add(ans);
        }

        if(type.equals("imageText") || type.equals("translate") || type.equals("write")||type.equals("hearingAndSentence")){
            List<String> strings = option.get(0);
            for(int i=0;i<strings.size();i++){
                strings.set(i,strings.get(i).substring(2));
            }
        }

        String str= JSONArray.fromObject(option).toString();
        String de=JSONArray.fromObject(des).toString();
        return new String[]{str,de};
      //是判断题需要把 选项转为 0 1
      /* if(type.equals("judge")){
         List<Integer>  ptionInt=new ArrayList<>();
           List<String> lastPtion = option.get(0);
           for(int i=0;i<lastPtion.size();i++){
               String str = lastPtion.get(i);
               if(str.equals("yes")){
                   ptionInt.add(1);
               }else{
                   ptionInt.add(0);
               }
           }
           List<List<Integer>>  lis=new ArrayList<>();
           lis.add(ptionInt);

       }else{
            //直接返回选项

       }*/

    }



    //题目解析
    private String  getTopic(Cell topic1,Cell topic2,Cell topic3,Cell topic4,Cell topic5) throws Exception{
        List<String>  top=new ArrayList<>();
        List<String> top1 = this.getlistStrings(topic1);
        if(top1!=null ){
            top.add(top1.get(0));
        }
        List<String> top2 = this.getlistStrings(topic2);
        if(top2!=null ){
            top.add(top2.get(0));
        }
        List<String> top3 = this.getlistStrings(topic3);
        if(top3!=null ){
            top.add(top3.get(0));
        }
        List<String> top4 = this.getlistStrings(topic4);
        if(top4!=null ){
            top.add(top4.get(0));
        }
        List<String> top5 = this.getlistStrings(topic5);
        if(top5!=null ){
            top.add(top5.get(0));
        }

        return new JSONArray().fromObject(top).toString();
    }


    //坐标 解析
    private int getCoordinate(Cell coorDinates,String analysisStage)throws Exception{
        String coorDinate = coorDinates.getStringCellValue().trim();
        String[] coors = coorDinate.split(">");

        if("languageTest".equals(analysisStage)){
            return (int)languageService.queryLanguageIdByName(coors[0]);
        }else if("courseCertificate".equals(analysisStage)){
            Integer languageId = languageService.queryLanguageIdByName(coors[0]);
            return  (int)courseService.queryCourseIdByName(languageId,coors[1]);
        }else{
            Integer languageId = languageService.queryLanguageIdByName(coors[0]);
            Integer courseId = courseService.queryCourseIdByName(languageId,coors[1]);
            Integer leavelId = levelService.queryLeavelIdByName(courseId,coors[2]);
            return (int)chapterService.queryChapterIdByName(leavelId,coors[3]);
        }
    }


    private List<String> getlistStrings(Cell option) {
        if (option == null) {
            return null;
        }
        try {
            String stage = option.getStringCellValue().trim();
            List<String> list = new ArrayList();
            String[] split = stage.split("\\)");
            for (String str : split) {
                String[] scores = str.trim().split("\\(");
                for (String score : scores) {
                    if (score != null && score.length() > 0) {
                        list.add(score);
                    }
                }
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 同步题库
     * @param userId
     */
    public void synchronizeQuestions(Integer userId) {
        List<QuestionBO> questionBOS = this.queryTest(userId);
        if(questionBOS!=null){
            for (int i=0;i<questionBOS.size();i++){
                this.addquestion(questionBOS.get(i));
            }
        }
        this.deleteTest(userId);
    }
}
