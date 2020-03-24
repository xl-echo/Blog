echo编辑整理，欢迎转载，转载请声明文章来源。欢迎添加echo微信(微信号：t2421499075)交流学习。 

---

> index界面是我们的首页，当我们开发的越来越多的时候，我们index界面的内容有可能会特别臃肿，这个时候我们需要将index界面进行拆分。分别拆分成为heard、home、footer这三个vue页面，然后将页面制作成为组件，在index界面引入。由于我们在heard中做了导航菜单栏，所以我们根据导航菜单栏的变动，更换home页面位置的内容。让index界面使用自己封装的组件实现动态加载界面内容。

- index.vue中只保留导航栏的基本内容
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200206180937657.png)

- 在home界面中存储index中间部分内容
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200206181033794.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hsZWNobw==,size_16,color_FFFFFF,t_70)

- footer内容就是页脚内容
![在这里插入图片描述](https://img-blog.csdnimg.cn/20200206181112363.png)

> 但我们的界面内容搬迁完成之后，我们需要在首页上将这三个界面的内容全部显示，这个时候我们就需要将页面变成index界面的组件。

- 封装组件
直接将home页面在index.vue中引入
```js
 import home from '../page/home/home.vue'
```

- 引入之后写一个components
```js
components: {
  'index-home': home,
},
```

- 然后就可以在我们的index里面作为组件使用了，使用代码如下
```vue
<index-home/>
```