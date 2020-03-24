echo编辑整理，欢迎转载，转载请声明文章来源。欢迎添加echo微信(微信号：t2421499075)交流学习。 

---

> vue中使用axios发送请求其实比较简单，这里我们简单应用，部分环境所以我们不需要很多的配置，具体步骤如下

- 在vue项目中安装axios请求
```
cnpm install axios -S
```

- 安装完成之后我们在main.js文件中引入axios
```
import axios from 'axios';
Vue.prototype.axios = axios;
```

> 这个时候其实我们就可以使用axios来发送请求了，但是这里有一个问题，它的默认请求前缀为localhost:8080，不利于我们访问后端或者远程的其他地址，所以我们需要自定义一个请求前缀

- 自定义请求前缀
```
axios.defaults.baseURL = 'http://localhost:10080';
```

- 请求示例
```
this.axios({
  method: "post",
  url: "/userController/login",
  headers: {"Content-type": "application/x-www-form-urlencoded"},
  params: {
    username: this.userInfo.username,
    password: this.userInfo.password
  }
}).then(res => {
  
});
```