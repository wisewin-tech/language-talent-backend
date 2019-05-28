<!DOCTYPE html>
<html>

<head>
    <title>Index</title>
    <style type="text/css">
    </style>
</head>

<body>
<div>
    <input id="file" type="file" />
    <button id="upload" type="button">upload</button>
</div>
<script type="text/javascript">
    let button = document.getElementById('upload')
    button.addEventListener('click', event => {
        let fileObj = document.getElementById('file').files[0]
        if (fileObj) {
            let url = '/question/importQuestions'
            let formData = new FormData()
            formData.append('file', fileObj)
            let xhr = new XMLHttpRequest()

            xhr.onload = function () {
                console.log('ok')
                console.log(JSON.parse(xhr.responseText))
            }
            xhr.onerror = function () {
                console.log('fail')
            }
            xhr.open('post', url, true)
            xhr.send(formData)

        } else {
            console.log('请选择文件')
    }
    })
</script>
</body>

</html>