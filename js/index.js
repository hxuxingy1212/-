new Vue({
    el:"#login-vue",
    data:{
        loading: true
    },
    methods: {
        //点击登陆按钮，发送请求判断是否登陆成功
        login_check() {
 
            // 发送后端请求
            // axios.get('http://43.142.107.197:8081/users/getUserByToken').then((res) => {
            //     //登陆成功，可以选择家庭身份
            //     if (res.data.flag == true) {
            //         this.uid = res.data.data.uid
            //         this.username = res.data.data.username
            //         this.password = res.data.data.password

            //         //初始化控制台数据
            //         this.init();

            //     //登陆失败
            //     } else {
            //         this.$message.warning("未登录");
            // }
            // })
        },
    },
    mounted() {
        axios.defaults.withCredentials = true;
        //每次请求携带token
        let token = sessionStorage.getItem("token")
        axios.interceptors.request.use(
            config => {
                if (token) config.headers['authorization'] = token
                return config
            },
            error => {
                console.log(error)
                return Promise.reject(error)
            }
        )
        
    }
    
    })