<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Axios</title>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script src="/plugins/vue/vue.js"></script>

    <script>
        axios.get('/c/page/test/get?userId=32')
            .then(function (response) {
                console.log(response);
            })
            .catch(function (error) {
                console.log(error);
            });


        function doGet() {

            axios.get('/c/page/test/get',{
                params:{
                    userId:10
                }
            })
                .then(function (response) {
                    console.log(response);
                    alert(response.data.bean.createTime);
                })
                .catch(function (error) {
                    console.log(error);
                });
        }
        
        function doPost() {

            axios.post('/c/page/test/post', {
                userId: 32,
                createTime: 123123123
            })
                    .then(function (response) {
                        console.log(response);
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
        }


        var app = new Vue({
            el: '#app',
            data: {
                message: 'Hello Vue!'
            }
        })
    </script>
</head>
<body>

    <button class="btn btn-info btn-lg" onclick="doGet();">GET请求</button>
    <button class="btn btn-info btn-lg" onclick="doPost();">POST请求</button>

    <div id="app">
        {{ message }}
    </div>

</body>
</html>
