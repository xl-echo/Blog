<template>
  <div class="register" style="position:absolute;top:35%;left:50%;transform: translate(-50%,-50%);">
    <Col align="center">
      <h1>
        注册账户
      </h1>
    </Col>
    <Card align="center" style="width: 450px">
      <Form ref="userInfo" :model="userInfo" :rules="userInfoRules">
        <FormItem prop="username">
          <Input type="text" v-model="userInfo.username" placeholder="请输入用户名"><span slot="prepend"><Icon :size="16"
                                                                                                         type="ios-person"/></span></Input>
        </FormItem>
        <FormItem prop="password">
          <Input type="password" v-model="userInfo.password" placeholder="请输入密码长度在5到25个字符,支持字母、数字、下划线"><span slot="prepend"><Icon :size="14"
                                                                                                            type="md-lock"/></span></Input>
        </FormItem>
        <FormItem prop="email">
          <Input type="text" v-model="userInfo.email" placeholder="请输入邮箱号码"><span slot="prepend"><Icon type="ios-mail"
                                                                                                       :size="16"/></span></Input>
        </FormItem>
        <FormItem prop="phone">
          <Input type="text" v-model="userInfo.phone" placeholder="请输入手机号码"><span slot="prepend"><Icon
            type="md-phone-portrait" :size="16"/></span></Input>
        </FormItem>
        <FormItem>
          <Button @click="register('userInfo')" long type="primary">注册</Button>
        </FormItem>
      </Form>
    </Card>
  </div>
</template>

<script>
  export default {
    name: "register",
    data() {
      return {
        userInfo: {
          username: '',
          password: '',
          email: '',
          phone: ''
        },
        userInfoRules: {
          username: [{required: true, message: "用户名不能为空", trigger: "blur"}],
          password: [{required: true, message: '请输入密码', trigger: 'blur'},
            {min: 5, max: 25, message: '长度在 5 到 25个字符'},
            {pattern: /^(\w){5,25}$/, message: '只能输入5-25个字母、数字、下划线'}],
          email: [{required: true, message: '请输入邮箱号', trigger: 'blur'},
            {
              validator: function (rule, value, callback) {
                if (value === '') {
                  callback(new Error('请正确填写邮箱'));
                } else {
                  if (value !== '') {
                    var reg = /^[A-Za-z0-9_\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
                    if (!reg.test(value)) {
                      callback(new Error('请输入有效的邮箱'));
                    }else {
                      callback();
                    }
                  } else {
                    callback();
                  }
                }
              }, trigger: 'blur'
            }],
          phone: [{required: true, message: '请输入手机号码', trigger: 'blur'},
            {
              validator: function (rule, value, callback) {
                if (/^1[34578]\d{9}$/.test(value) == false) {
                  callback(new Error("请输入正确的手机号"));
                } else {
                  callback();
                }
              }, trigger: 'blur'
            }],
        }
      }
    },
    methods: {
      register: function (name) {
        this.$refs[name].validate(valid => {
          if (valid) {
            this.axios({
              method: "post",
              url: "/userController/register",
              headers: {"Content-type": "application/json"},
              data: this.userInfo
            }).then(res => {
              if (this.exception(res)) {
                this.$router.push('/login')
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

    }
  }
</script>

<style scoped>

</style>
