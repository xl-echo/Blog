<template>
  <div class="login" style="position:absolute;top:35%;left:50%;transform: translate(-50%,-50%);">
    <Col align="center">
      <h1>
        注册登录
      </h1>
    </Col>
    <Card align="center" style="height: 240px;line-height: 240px;width: 450px">
      <Form ref="userInfo" :model="userInfo" :rules="userInfoRules">
        <FormItem prop="username">
          <Input type="text" v-model="userInfo.username" placeholder="请输入用户名"><span slot="prepend"><Icon :size="16"
                                                                                                         type="ios-person"/></span></Input>
        </FormItem>
        <FormItem prop="password">
          <Input type="password" v-model="userInfo.password" placeholder="请输入密码"><span slot="prepend"><Icon :size="14"
                                                                                                            type="md-lock"/></span></Input>
        </FormItem>
        <FormItem>
          <Button @click="handleSubmit('userInfo')" type="primary" long>登录</Button>
        </FormItem>
        <FormItem>
          <Button @click="register" long>注册</Button>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
  export default {
    name: "login",
    data() {
      return {
        userInfo: {
          username: '',
          password: ''
        },
        userInfoRules: {
          username: [{required: true, message: "用户名不能为空", trigger: "blur"}],
          password: [{required: true, message: "密码不能为空", trigger: "blur"}]
        }
      }
    },
    methods: {
      register: function () {
        this.$router.push('/register')
      },
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
                localStorage.setItem("authorId", res.data.data.id);
                this.$router.push('/')
              }
            });
          }
        });
      },
      exception: function (res) {
        if (res.data.status === 999) {
          this.$Modal.info({
            title: "请求失败",
            content: res.data.message
          });
          return false;
        } else if (res.data.status === 1000) {
          this.$Modal.info({
            title: "温馨提示",
            content: res.data.message
          });
          return false;
        } else if (res.data.status === 200) {
          return true;
        }
      }

    },
  }
</script>

<style scoped>

</style>
