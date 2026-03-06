<template>
  <div class="container">
    <div class="projectPage">
      <Card title="开源项目">
        <div class="project-list">
          <div class="project-item" v-for="(project, index) in projects" :key="project.id">
            <div class="project-cover">
              <img :src="project.cover || '/default-project-cover.jpg'" :alt="project.name"/>
            </div>
            <div class="project-content">
              <h3 class="project-name">
                <a :href="project.url" target="_blank" rel="noopener noreferrer">{{ project.name }}</a>
              </h3>
              <p class="project-description">{{ project.description }}</p>
              <div class="project-meta">
                <span class="project-language">{{ project.language }}</span>
                <span class="project-star">
                  <i class="icon ion-ios-star"></i> {{ project.starCount || 0 }}
                </span>
                <span class="project-fork">
                  <i class="icon ion-ios-git-branch"></i> {{ project.forkCount || 0 }}
                </span>
              </div>
              <div class="project-tags">
                <span class="tag" v-for="(tag, index) in project.tags" :key="index">{{ tag }}</span>
              </div>
            </div>
          </div>
        </div>
        <!-- 分页 -->
        <Pagination
          v-if="total > 0"
          :total="total"
          :page-size="pageSize"
          :current="currentPage"
          @on-change="pageChange"
          show-total
          show-sizer
          show-jumper>
        </Pagination>
      </Card>
    </div>
  </div>
</template>

<script>
export default {
  name: 'projectPage',
  data() {
    return {
      projects: [],
      total: 0,
      pageSize: 10,
      currentPage: 1,
      isLoading: false,
      username: localStorage.getItem('username')
    }
  },
  created() {
    this.loadProjects()
  },
  methods: {
    async loadProjects() {
      this.isLoading = true
      try {
        const res = await this.axios.get('/projectController/list', {
          params: {
            page: this.currentPage,
            size: this.pageSize
          }
        })
        if (res.data.status === 999) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 1000) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 200) {
          this.projects = res.data.data.list
          this.total = res.data.data.total
        }
      } catch (error) {
        this.$Message.error('加载项目失败，请稍后重试')
        console.error(error)
      } finally {
        this.isLoading = false
      }
    },
    pageChange(page) {
      this.currentPage = page
      this.loadProjects()
    },
    async addProject() {
      const username = localStorage.getItem('username')
      if (username === '' || username === null) {
        this.$Message.error('请先登录')
        return
      }
      const project = {
        name: '',
        url: '',
        description: '',
        language: '',
        tags: '',
        cover: '',
        username: username
      }
      try {
        const res = await this.axios.post('/projectController/add', project, {
          params: {
            token: localStorage.getItem('token')
          }
        })
        if (res.data.status === 999) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 1000) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 200) {
          this.$Message.success('项目添加成功')
          this.loadProjects()
        }
      } catch (error) {
        this.$Message.error('项目添加失败，请稍后重试')
        console.error(error)
      }
    },
    async deleteProject(id) {
      const ifPay = confirm('确定要删除这个项目吗？')
      if (ifPay === 0) {
        return
      }
      try {
        const res = await this.axios.post('/projectController/delete', null, {
          params: {
            id: id,
            token: localStorage.getItem('token')
          }
        })
        if (res.data.status === 999) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 1000) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 200) {
          this.$Message.success('项目删除成功')
          this.loadProjects()
        }
      } catch (error) {
        this.$Message.error('项目删除失败，请稍后重试')
        console.error(error)
      }
    },
    async updateProject(project) {
      if (project.id === 1) {
        this.$Message.error('默认项目不能修改')
        return
      }
      try {
        const res = await this.axios.post('/projectController/update', project, {
          params: {
            token: localStorage.getItem('token')
          }
        })
        if (res.data.status === 999) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 1000) {
          this.$Message.error(res.data.msg)
        } else if (res.data.status === 200) {
          this.$Message.success('项目更新成功')
          this.loadProjects()
        }
      } catch (error) {
        this.$Message.error('项目更新失败，请稍后重试')
        console.error(error)
      }
    }
  }
}
</script>

<style scoped>
.projectPage {
  margin: 20px;
}
.ivu-card {
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.project-list {
  margin-bottom: 30px;
}
.project-item {
  display: flex;
  margin-bottom: 20px;
  padding: 20px;
  background: white;
  border-radius: 8px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  transition: all 0.3s;
}
.project-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 4px 16px 0 rgba(0, 0, 0, 0.15);
}
.project-cover {
  width: 150px;
  height: 150px;
  margin-right: 20px;
  flex-shrink: 0;
}
.project-cover img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  border-radius: 4px;
}
.project-content {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
.project-name {
  margin: 0 0 10px 0;
  font-size: 20px;
}
.project-name a {
  color: #303133;
  text-decoration: none;
}
.project-name a:hover {
  color: #409eff;
}
.project-description {
  margin: 0 0 15px 0;
  font-size: 14px;
  color: #606266;
  line-height: 1.6;
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.project-meta {
  margin: 0 0 15px 0;
  font-size: 14px;
  color: #909399;
}
.project-language {
  margin-right: 15px;
  padding: 2px 8px;
  background: #f0f2f5;
  color: #606266;
  border-radius: 4px;
  font-size: 12px;
}
.project-star {
  margin-right: 15px;
}
.project-fork {
  margin-right: 15px;
}
.project-meta i {
  margin-right: 5px;
}
.project-tags {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
}
.tag {
  padding: 2px 8px;
  background: #ecf5ff;
  color: #409eff;
  border-radius: 4px;
  font-size: 12px;
}
.ivu-page {
  margin-top: 30px;
  text-align: center;
}
</style>
