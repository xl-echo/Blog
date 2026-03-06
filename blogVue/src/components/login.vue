<template>
  <div class="container">
    <div class="login" style="position:absolute;top:35%;left:50%;transform: translate(-50%,-50%);">
      <Col align="center">
        <h1>欢迎登录博客系统</h1>
        <p style="color: #666; margin-bottom: 20px;">
          首次登录将自动创建账号，登录后可发布文章、点赞和评论
        </p>
      </Col>
      <Card align="center" style="height: 280px;line-height: 280px;width: 450px">
        <Form ref="userInfo" :model="userInfo" :rules="userInfoRules">
          <FormItem prop="username">
            <Input type="text" v-model="userInfo.username" placeholder="请输入用户名">
              <span slot="prepend">
                <Icon :size="16" type="ios-person"/>
              </span>
            </Input>
          </FormItem>
          <FormItem prop="password">
            <Input type="password" v-model="userInfo.password" placeholder="请输入密码">
              <span slot="prepend">
                <Icon :size="14" type="md-lock"/>
              </span>
            </Input>
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
  </div>
</template>

<script>
export default {
  name: 'login',
  data() {
    return {
      userInfo: {
        username: '',
        password: ''
      },
      userInfoRules: {
        username: [{ required: true, message: '用户名不能为空', trigger: 'blur' }],
        password: [{ required: true, message: '密码不能为空', trigger: 'blur' }]
      }
    }
  },
  methods: {
    register() {
      this.$router.push('/register')
    },
    handleSubmit(name) {
      this.$refs[name].validate(valid => {
        if (valid) {
          this.axios({
            method: 'post',
            url: '/userController/login',
            data: this.userInfo
          }).then(res => {
            if (res.data.status === 200) {
              localStorage.setItem('token', res.data.data.token)
              localStorage.setItem('username', res.data.data.username)
              localStorage.setItem('authorId', res.data.data.id)
              this.$Message.success('登录成功')
              this.$router.push('/home')
            } else {
              this.$Message.error(res.data.message || '登录失败')
            }
          }).catch(error => {
            this.$Message.error('登录失败，请稍后重试')
            console.error(error)
          })
        }
      })
    }
  }
}
</script>

<style scoped>
.login {
  width: 100%;
}
</style>
