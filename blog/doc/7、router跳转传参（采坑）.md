echo编辑整理，欢迎转载，转载请声明文章来源。欢迎添加echo微信(微信号：t2421499075)交流学习。 

---

> 当我们完成基本的首页文章列表加载之后，我们就需要制作一个点击加载详情的动作。我们在首页看到的每一个文件简介都可以点击，点击之后应该跳转到详情页，但是详情页到底对应的是哪篇文章的详情呢？所以这里我们就需要使用router传参.

- 首先在我们在循环中，给每一个标题都加上一个点击事件，并传入在循环中获取的文章id
```html
<Col v-for="item in contentList">
  <Card style="margin: 10px;">
    <Button type="primary" size="small">{{item.ifOriginal | formatType}}</Button>
    <B style="margin-left: 6px;font-size: 16px" @click="detail(item.id)">{{item.title}}</B>
    <p style="margin-top: 5px">{{item.content}}</p>
    <p style="margin-top: 5px">{{item.createTime | formatDate}}&nbsp;&nbsp;阅读数: {{item.clicks}}</p>
  </Card>
</Col>
```

- 当我们点击文章标题的时候，使用router传递文章id并跳转页面
```js
detail: function (id) {
    this.$router.push({name: 'pageDetail', params: {articleId: id}});
},
```

- 进入详情页之后我们在详情页面上获取传递过去的参数
```js
created() {
  this.getDetail();
},
methods: {
  getDetail: function () {
    this.articleId = this.$route.params.articleId;
  }
},
```

- 注意：在我们的router传递参数的时候有两种方式，一种类似于get请求方式会将我们需要传递的参数拼接到地址栏。具体代码如下；另外一种类似于post传值方式在地址栏看不到，用法如上代码；
```vue
this.$router.push({path: '/pageDetail', query: {articleId: id}});
this.$route.query.articleId;
```

- 采坑指南：千万要注意传值是使用$router,获取值是使用$route
