/**
 * API工具类
 * 用于封装博客系统的API调用
 */

// 获取token
function getToken() {
  return localStorage.getItem('token')
}

// 获取用户ID
function getUserId() {
  return localStorage.getItem('authorId')
}

// 获取用户名
function getUsername() {
  return localStorage.getItem('username')
}

// 检查是否登录
function isLoggedIn() {
  return !!getToken()
}

/**
 * 点赞文章
 * @param {Number} articleId 文章ID
 */
export function likeArticle(articleId) {
  if (!isLoggedIn()) {
    return Promise.reject({ message: '请先登录' })
  }
  
  return this.axios.post('/likeController/like', null, {
    params: {
      articleId: articleId,
      userId: getUserId(),
      username: getUsername(),
      token: getToken()
    }
  })
}

/**
 * 取消点赞文章
 * @param {Number} articleId 文章ID
 */
export function unlikeArticle(articleId) {
  if (!isLoggedIn()) {
    return Promise.reject({ message: '请先登录' })
  }
  
  return this.axios.post('/likeController/unlike', null, {
    params: {
      articleId: articleId,
      userId: getUserId(),
      token: getToken()
    }
  })
}

/**
 * 检查是否已点赞
 * @param {Number} articleId 文章ID
 */
export function checkLiked(articleId) {
  return this.axios.get('/likeController/check', {
    params: {
      articleId: articleId,
      userId: getUserId() || 0
    }
  })
}

/**
 * 获取点赞数
 * @param {Number} articleId 文章ID
 */
export function getLikeCount(articleId) {
  return this.axios.get('/likeController/count', {
    params: {
      articleId: articleId
    }
  })
}

/**
 * 添加评论
 * @param {Object} comment 评论对象
 */
export function addComment(comment) {
  if (!isLoggedIn()) {
    return Promise.reject({ message: '请先登录' })
  }
  
  return this.axios.post('/commentController/add', comment, {
    params: {
      token: getToken()
    }
  })
}

/**
 * 获取文章评论列表
 * @param {Number} articleId 文章ID
 */
export function getComments(articleId) {
  return this.axios.get('/commentController/list', {
    params: {
      articleId: articleId
    }
  })
}

/**
 * 删除评论
 * @param {Number} commentId 评论ID
 */
export function deleteComment(commentId) {
  if (!isLoggedIn()) {
    return Promise.reject({ message: '请先登录' })
  }
  
  return this.axios.post('/commentController/delete', null, {
    params: {
      id: commentId,
      token: getToken()
    }
  })
}

export default {
  likeArticle,
  unlikeArticle,
  checkLiked,
  getLikeCount,
  addComment,
  getComments,
  deleteComment,
  getToken,
  getUserId,
  getUsername,
  isLoggedIn
}
