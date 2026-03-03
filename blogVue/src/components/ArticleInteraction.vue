<template>
  <div class="article-interaction">
    <!-- 点赞区域 -->
    <div class="like-section">
      <Button 
        :type="isLiked ? 'error' : 'default'"
        :icon="isLiked ? 'md-heart' : 'md-heart-outline'"
        @click="handleLike"
        :loading="likeLoading"
      >
        {{ isLiked ? '已点赞' : '点赞' }} ({{ likeCount }})
      </Button>
    </div>

    <!-- 评论区域 -->
    <div class="comment-section">
      <div class="comment-input">
        <Input 
          v-model="newComment" 
          type="textarea" 
          :rows="3" 
          placeholder="登录后发表评论..."
          :disabled="!isLoggedIn"
        />
        <Button 
          type="primary" 
          @click="submitComment" 
          :disabled="!isLoggedIn || !newComment.trim()"
          style="margin-top: 10px;"
          :loading="commentLoading"
        >
          发表评论
        </Button>
        <div v-if="!isLoggedIn" style="color: #999; font-size: 12px; margin-top: 5px;">
          请先登录后再发表评论
        </div>
      </div>

      <!-- 评论列表 -->
      <div class="comment-list" v-if="comments.length > 0">
        <h4>评论 ({{ comments.length }})</h4>
        <div class="comment-item" v-for="(comment, index) in comments" :key="comment.id">
          <div class="comment-header">
            <span class="comment-user">{{ comment.username }}</span>
            <span class="comment-time">{{ formatTime(comment.createTime) }}</span>
          </div>
          <div class="comment-content">{{ comment.content }}</div>
          <Button 
            v-if="canDelete(comment)" 
            type="text" 
            size="small" 
            @click="deleteComment(comment.id)"
            style="color: #ed4014;"
          >
            删除
          </Button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/utils/api'

export default {
  name: 'ArticleInteraction',
  props: {
    articleId: {
      type: Number,
      required: true
    }
  },
  data() {
    return {
      isLiked: false,
      likeCount: 0,
      likeLoading: false,
      comments: [],
      newComment: '',
      commentLoading: false,
      isLoggedIn: api.isLoggedIn()
    }
  },
  mounted() {
    this.loadLikeStatus()
    this.loadComments()
  },
  methods: {
    // 加载点赞状态
    loadLikeStatus() {
      if (this.isLoggedIn) {
        api.checkLiked(this.articleId).then(res => {
          if (res.data.status === 200) {
            this.isLiked = res.data.data
          }
        })
      }
      this.loadLikeCount()
    },

    // 加载点赞数
    loadLikeCount() {
      api.getLikeCount(this.articleId).then(res => {
        if (res.data.status === 200) {
          this.likeCount = res.data.data
        }
      })
    },

    // 处理点赞
    handleLike() {
      if (!this.isLoggedIn) {
        this.$Message.warning('请先登录后再点赞')
        this.$router.push('/login')
        return
      }

      this.likeLoading = true
      const action = this.isLiked ? api.unlikeArticle : api.likeArticle
      
      action.call(this, this.articleId).then(res => {
        if (res.data.status === 200) {
          this.isLiked = !this.isLiked
          this.likeCount += this.isLiked ? 1 : -1
          this.$Message.success(res.data.data)
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch(err => {
        this.$Message.error(err.message || '操作失败')
      }).finally(() => {
        this.likeLoading = false
      })
    },

    // 加载评论
    loadComments() {
      api.getComments(this.articleId).then(res => {
        if (res.data.status === 200) {
          this.comments = res.data.data
        }
      })
    },

    // 提交评论
    submitComment() {
      if (!this.isLoggedIn) {
        this.$Message.warning('请先登录后再发表评论')
        this.$router.push('/login')
        return
      }

      if (!this.newComment.trim()) {
        this.$Message.warning('评论内容不能为空')
        return
      }

      this.commentLoading = true
      const comment = {
        articleId: this.articleId,
        userId: parseInt(api.getUserId()),
        username: api.getUsername(),
        content: this.newComment.trim()
      }

      api.addComment(comment).then(res => {
        if (res.data.status === 200) {
          this.$Message.success('评论成功')
          this.newComment = ''
          this.loadComments()
        } else {
          this.$Message.error(res.data.message)
        }
      }).catch(err => {
        this.$Message.error(err.message || '评论失败')
      }).finally(() => {
        this.commentLoading = false
      })
    },

    // 删除评论
    deleteComment(commentId) {
      this.$Modal.confirm({
        title: '确认删除',
        content: '确定要删除这条评论吗？',
        onOk: () => {
          api.deleteComment(commentId).then(res => {
            if (res.data.status === 200) {
              this.$Message.success('删除成功')
              this.loadComments()
            } else {
              this.$Message.error(res.data.message)
            }
          }).catch(err => {
            this.$Message.error(err.message || '删除失败')
          })
        }
      })
    },

    // 判断是否可以删除评论
    canDelete(comment) {
      return this.isLoggedIn && comment.userId === parseInt(api.getUserId())
    },

    // 格式化时间
    formatTime(time) {
      if (!time) return ''
      const date = new Date(time)
      const now = new Date()
      const diff = now - date
      
      if (diff < 60000) return '刚刚'
      if (diff < 3600000) return Math.floor(diff / 60000) + '分钟前'
      if (diff < 86400000) return Math.floor(diff / 3600000) + '小时前'
      if (diff < 2592000000) return Math.floor(diff / 86400000) + '天前'
      
      return date.toLocaleDateString()
    }
  }
}
</script>

<style scoped>
.article-interaction {
  margin-top: 20px;
  padding: 20px;
  background: #f8f8f9;
  border-radius: 4px;
}

.like-section {
  margin-bottom: 20px;
}

.comment-section {
  border-top: 1px solid #e8eaec;
  padding-top: 20px;
}

.comment-input {
  margin-bottom: 20px;
}

.comment-list h4 {
  margin-bottom: 15px;
  color: #333;
}

.comment-item {
  padding: 15px;
  background: white;
  border-radius: 4px;
  margin-bottom: 10px;
  border: 1px solid #e8eaec;
}

.comment-header {
  display: flex;
  justify-content: space-between;
  margin-bottom: 8px;
}

.comment-user {
  font-weight: bold;
  color: #2d8cf0;
}

.comment-time {
  color: #999;
  font-size: 12px;
}

.comment-content {
  color: #333;
  line-height: 1.6;
  margin-bottom: 8px;
}
</style>
