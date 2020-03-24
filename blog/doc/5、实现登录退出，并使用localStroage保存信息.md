echo编辑整理，欢迎转载，转载请声明文章来源。欢迎添加echo微信(微信号：t2421499075)交流学习。 

---

> 登录界面上我们操作登录，当我们登录成功就会有一个问题，需要跳转回原来的界面并且保存显示我们的登录信息。

- 这里我们首先来登录
```
handleSubmit: function (name) {
    this.$refs[name].validate(valid => {
      if (valid) {
        this.axios({
          method: "post",
          url: "/userController/login",
          headers: {"Content-type": "application/x-www-form-urlencoded"},
          params: {
            username: this.userInfo.username,
            password: this.userInfo.password
          }
        }).then(res => {
          if (this.exception(res)) {
            localStorage.setItem("token", res.data.data.token);
            localStorage.setItem("username", res.data.data.username);
            this.$router.push('/')
          }
        });
      }
    });
}
```

- 登录成功之后，我们直接使用localStroage.setItem来保存我们需要保存的信息。保存代码如下(并附加localStroage的相关方法)：
```
localStorage.setItem("token", res.data.data.token);
localStorage.getItem("key")//获取名称为"key"的值
localStorage.removeItem("key")//删除名称为"key"的信息
localStorage.clear() //清空localstorage中所有的信息
```

- 登录成功之后我们直接使用人router跳转到index界面，然后在index写一个页面加载读取localStroage的方法，让我们的用户信息显示出来
```
created() {
  let token = localStorage.getItem("token");
  if (token == '' || token == null) {
    this.loginBefore = true;
    this.loginAfter = false;
  } else {
    this.loginBefore = false;
    this.loginAfter = true;
    this.username = localStorage.getItem("username");
  }
},
```

- 编写下来菜单的点击事件，完成退出并刷新界面
```
<Dropdown v-if="loginAfter" @on-click="dropdownMethod">
    <Button type="primary">
      {{username}}
      <Icon type="logo-octocat" size="24"/>
    </Button>
    <DropdownMenu slot="list" align="center">
      <DropdownItem name="homePage">个人主页</DropdownItem>
      <DropdownItem name="addBlog">写博客</DropdownItem>
      <DropdownItem name="blogManagement">博客管理</DropdownItem>
      <DropdownItem name="loginOut">退出</DropdownItem>
    </DropdownMenu>
</Dropdown>

dropdownMethod: function (name) {
    if (name == 'homePage') {
      alert('homePage')
    } else if (name == 'addBlog') {
      alert('addBlog')
    } else if (name == 'blogManagement') {
      alert('blogManagement')
    } else if (name == 'loginOut') {
      this.loginOut();
    }
},
loginOut: function () {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    location.reload();
    this.$router.go(0);
}
```