package com.wisewin.backend.service;

import com.wisewin.backend.dao.QuestionDAO;
import com.wisewin.backend.entity.bo.AnsDesBO;
import com.wisewin.backend.entity.bo.ChapterIdBO;
import com.wisewin.backend.entity.bo.QuestionBO;
import net.sf.json.JSONArray;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private LanguageService languageService;
    @Resource
    private CourseService courseService;
    @Resource
    private LevelService levelService;
    @Resource
    private ChapterService chapterService;

    /**
     * 添加题目到题库
     *
     * @param questionBO
     * @return
     */
    public Integer addquestion(QuestionBO questionBO) {
        return questionDAO.addquestion(questionBO);
    }

    /**
     * 删除测试题库
     */
    public void deleteTest(Integer userId) {
        questionDAO.deleteTest(userId);
    }

    /**
     * 查询此次添加数据
     */
    public List<QuestionBO> queryTest(Integer userId) {
        return questionDAO.queryTest(userId);
    }

    /**
     * 添加测试题库
     */

    public Integer addquestionTest(QuestionBO questionBO) {
        return questionDAO.addquestionTest(questionBO);
    }

    /**
     * 查询题库
     *
     * @param map
     * @return
     */
    public List<QuestionBO> selectQuestion(Map<String, Object> map) {
        return questionDAO.selectQuestion(map);
    }

    /**
     * 查询总条数
     *
     * @param mapParam
     * @return
     */
    public Integer selectbylimitCount(Map<String, Object> mapParam) {
        return questionDAO.selectbylimitCount(mapParam);
    }

    /**
     * 修改题目
     *
     * @param questionBO
     * @return
     */
    public boolean updateQuestion(QuestionBO questionBO) {
        return questionDAO.updateQuestion(questionBO);
    }

    /**
     * 查询某题的所有信息
     *
     * @param id
     * @return
     */
    public QuestionBO getQuestion(Integer id) {
        return questionDAO.getQuestion(id);
    }

    //通过课程id查找语言id
    public ChapterIdBO getCourseId(Integer id) {
        return questionDAO.getCourseId(id);
    }

    //通过课时id查找课程id级别id语言id
    public ChapterIdBO getChapterId(Integer id) {
        return questionDAO.getChapterId(id);
    }

    /**
     * 删除题目
     *
     * @param idArr
     * @return
     */
    public Integer delQuestion(Integer[] idArr) {
        return questionDAO.delQuestion(idArr);
    }

    /**
     * Excel 导入试题
     *
     * @param file
     */
    public Integer importQuestions(MultipartFile file, Integer userId) {
        int rows = 1;
        try {
            Workbook wb;
            try {
                wb = new XSSFWorkbook(file.getInputStream());
            }catch (Exception e){
                try {
                    wb = new HSSFWorkbook(file.getInputStream());
                }catch (Exception e1){
                    return -1;
                }
            }
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                rows++;
                Row row = sheet.getRow(i);// 每一行
                if(row==null){
                    break;
                }
                QuestionBO questionBO = new QuestionBO();
                questionBO.setQuestionType(this.analysisType(row.getCell(0))); //题类型
                questionBO.setTestType(this.analysisStage(row.getCell(1))); //阶段
                questionBO.setScore(this.analysisAnswer(row.getCell(2))); //分值解析
                String[] answer = this.analysisContent(questionBO.getTestType(),row.getCell(4), row.getCell(3), questionBO.getQuestionType(), row.getCell(11), row.getCell(12)
                        , row.getCell(13), row.getCell(14), row.getCell(15), row.getCell(16), row.getCell(17),
                        row.getCell(18), row.getCell(19), row.getCell(20), row.getCell(21), row.getCell(22),
                        row.getCell(23), row.getCell(24), row.getCell(25),row.getCell(26),row.getCell(27),row.getCell(28)
                        ,row.getCell(29),row.getCell(30),row.getCell(31),row.getCell(32),row.getCell(33),row.getCell(34),row.getCell(35));
                questionBO.setAnswer(answer[1]); //答案 和解析
                questionBO.setOption(answer[0]);//选项
                questionBO.setTopic(this.getTopic(row.getCell(6), row.getCell(7), row.getCell(8), row.getCell(9), row.getCell(10)));
                questionBO.setRelevanceId(this.getCoordinate(row.getCell(5), questionBO.getTestType()));
                questionBO.setCreateTime(new Date());
                questionBO.setCreateUserId(userId);

                questionDAO.addquestionTest(questionBO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return rows;
        }
        return null;
    }


    //解析类型
    private String analysisType(Cell cell) throws Exception {
        String type = cell.getStringCellValue().trim();
        if ("常规".equals(type)) {
            return "common";
        } else if ("判断".equals(type)) {
            return "judge";
        } else if ("翻译".equals(type)) {
            return "translate";
        } else if ("拼写".equals(type)) {
            return "write";
        } else if ("听力和图文匹配".equals(type)) {
            return "hearingAndTest";
        } else if ("听音完成句子".equals(type)) {
            return "hearingAndSentence";
        } else if ("图文匹配".equals(type)) {
            return "imageText";
        } else if ("阅读".equals(type)) {
            return "read";
        } else {
            throw new RuntimeException();
        }
    }


    //阶段解析
    private String analysisStage(Cell cell) throws Exception {
        String stage = cell.getStringCellValue().trim();
        if ("语言能力测试".equals(stage)) {
            return "languageTest";
        } else if ("学前热身".equals(stage)) {
            return "warmUp";
        } else if ("随堂测试".equals(stage)) {
            return "test";
        } else if ("课程考证".equals(stage)) {
            return "courseCertificate";
        } else {
            throw new RuntimeException();
        }
    }


    //分值解析
    private String analysisAnswer(Cell cell) throws Exception {
        cell.setCellType(CellType.STRING);
        String stage = cell.getStringCellValue().trim();
        List<Integer> list = new ArrayList<Integer>();
        String[] split = stage.split("\\)");
        for (String str : split) {
            String[] scores = str.trim().split("\\(");
            for (String score : scores) {
                if (score != null && score.length() > 0) {
                    list.add(Integer.parseInt(score));
                }
            }
        }
        return JSONArray.fromObject(list).toString();
    }


    //选项 & 答案 & 解析    解析
    private String[] analysisContent(String testType,Cell answers, Cell analysiss, String type, Cell... optionss) throws Exception {
        //选项
        List<List<String>> option = new ArrayList<List<String>>();

        for (Cell cell : optionss) {
            List<String> opt = this.getlistStrings(cell);
            if (opt != null && opt.size()>0) {
                option.add(opt);
            }else {
                break;
            }
        }

        //答案
        List<String> answer = this.getlistStrings(answers);
        //解析
        List<String> analysis = this.getlistStrings(analysiss);

        List<AnsDesBO> des = new ArrayList<AnsDesBO>();
        List<List<String>> lists = topiceChange(option);

        //学前热身有可能没有答案和解析
        if( (!testType.equals("warmUp") )|| (testType.equals("warmUp") && answer!=null && answer.size()>0 ) ) {

            //阅读题处理
            if (type.equals("read")) {
                for (int i = 0, y = lists.size(); i < y; i++) {
                    AnsDesBO<Integer> ans = new AnsDesBO<Integer>();
                    for (int x = 0; x < lists.get(i).size(); x++) {
                        String subStr = lists.get(i).get(x).substring(0, 1);
                        if (subStr.equalsIgnoreCase(answer.get(i))) {
                            ans.setAns(x);
                            if (analysis != null && analysis.size() > 0 && analysis.size() < i)
                                ans.setDes(analysis.get(i));
                            des.add(ans);
                            break;
                        }
                    }
                }
                // 拼写 听音完成句子
            } else if (type.equals("write") || type.equals("hearingAndSentence")) {
                List<String> ops = lists.get(0);
                AnsDesBO<String> ans = new AnsDesBO<String>();
                if (analysis != null && analysis.size() > 0)
                    ans.setDes(analysis.get(0));
                StringBuffer strBuff = new StringBuffer("");
                for (int i = 0; i < answer.size(); i++) {  // 循环答案
                    for (int x = 0; x < ops.size(); x++) {
                        String subStr = ops.get(x).substring(0, 1);
                        if (subStr.equalsIgnoreCase(answer.get(i))) {
                            strBuff.append(x).append(",");
                            break;
                        }
                    }
                }
                ans.setAns(strBuff.toString());
                des.add(ans);
            } else {
                List<String> ops = lists.get(0);
                for (int i = 0; i < answer.size(); i++) {  // 循环答案
                    AnsDesBO<Integer> ans = new AnsDesBO<Integer>();
                    for (int x = 0; x < ops.size(); x++) {
                        String subStr = ops.get(x).substring(0, 1);
                        if (subStr.equalsIgnoreCase(answer.get(i))) {
                            ans.setAns(x);
                            if (analysis != null && analysis.size() > 0)
                                ans.setDes(analysis.get(i));
                            des.add(ans);
                            break;
                        }
                    }
                }
            }
        }

        //学热身可能没有答案和解析
        if((!testType.equals("warmUp") )|| (testType.equals("warmUp") && answer!=null && answer.size()>0 ) ) {
            if (type.equals("imageText") || type.equals("translate") || type.equals("write") || type.equals("hearingAndSentence")) {
                List<String> strings = option.get(0);
                for (int i = 0; i < strings.size(); i++) {
                    strings.set(i, strings.get(i).substring(2));
                }
                lists.set(0, strings);
            }
        }

        String str = JSONArray.fromObject(lists).toString();
        String de = JSONArray.fromObject(des).toString();
        return new String[]{str, de};
    }

    private  List<List<String>> topiceChange(List<List<String>> list) {
        List<List<String>>  resultList=new ArrayList<List<String>>();
        for(int x=0;x<list.get(0).size();x++){
            List<String>  clist=new ArrayList<String>();
            for(int i=0;i<list.size();i++){
                clist.add(list.get(i).get(x));
            }
            resultList.add(clist);
        }

        return resultList;
    }


    //题目解析
    private String getTopic(Cell... topic) throws Exception {
        List<String> tops = new ArrayList<String>();
        for(Cell cell:topic){
            List<String> top = this.getlistStrings(cell);
            if (top != null && top.size()>0) {
                tops.add(top.get(0));
            }else{
                break;
            }
        }
        return new JSONArray().fromObject(tops).toString();
    }


    //坐标 解析
    private int getCoordinate(Cell coorDinates, String analysisStage) throws Exception {
        String coorDinate = coorDinates.getStringCellValue().trim();
        String[] coors = coorDinate.split(">");

        if ("languageTest".equals(analysisStage)) {
            return (int) languageService.queryLanguageIdByName(coors[0]);
        } else if ("courseCertificate".equals(analysisStage)) {
            Integer languageId = languageService.queryLanguageIdByName(coors[0]);
            return (int) courseService.queryCourseIdByName(languageId, coors[1]);
        } else {
            Integer languageId = languageService.queryLanguageIdByName(coors[0]);
            Integer courseId = courseService.queryCourseIdByName(languageId, coors[1]);
            Integer leavelId = levelService.queryLeavelIdByName(courseId, coors[2]);
            return (int) chapterService.queryChapterIdByName(leavelId, coors[3]);
        }
    }


    private List<String> getlistStrings(Cell option) {
        if (option == null) {
            return null;
        }
        try {
            String stage = option.getStringCellValue().trim();
            List<String> list = new ArrayList<String>();
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
     *
     * @param userId
     */
    public void synchronizeQuestions(Integer userId) {
        List<QuestionBO> questionBOS = this.queryTest(userId);
        if (questionBOS != null) {
            for (int i = 0; i < questionBOS.size(); i++) {
                this.addquestion(questionBOS.get(i));
            }
        }
        this.deleteTest(userId);
    }
}
