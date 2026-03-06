// 时间格式化工具类

/**
 * 格式化日期
 * @param {Date|string|number} date - 日期对象、日期字符串或时间戳
 * @param {string} fmt - 格式化模板，如 'yyyy-MM-dd hh:mm:ss'
 * @returns {string} 格式化后的日期字符串
 */
export function formatDate(date, fmt) {
  // 参数校验
  if (date === null || date === undefined || date === '') {
    return ''
  }

  // 如果是字符串或数字，转换为Date对象
  let d = date
  if (!(date instanceof Date)) {
    d = new Date(date)
  }

  // 检查是否为有效日期
  if (isNaN(d.getTime())) {
    return ''
  }

  // 默认格式
  if (!fmt) {
    fmt = 'yyyy-MM-dd hh:mm:ss'
  }

  const o = {
    'M+': d.getMonth() + 1, // 月份
    'd+': d.getDate(), // 日
    'h+': d.getHours(), // 小时
    'm+': d.getMinutes(), // 分
    's+': d.getSeconds(), // 秒
    'q+': Math.floor((d.getMonth() + 3) / 3), // 季度
    'S': d.getMilliseconds() // 毫秒
  }

  // 处理年份
  if (/(y+)/.test(fmt)) {
    fmt = fmt.replace(RegExp.$1, (d.getFullYear() + '').substr(4 - RegExp.$1.length))
  }

  // 处理其他格式
  for (let k in o) {
    if (new RegExp('(' + k + ')').test(fmt)) {
      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ?
        (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)))
    }
  }

  return fmt
}

/**
 * 格式化日期为 yyyy-MM-dd
 * @param {Date|string|number} date
 * @returns {string}
 */
export function formatDateOnly(date) {
  return formatDate(date, 'yyyy-MM-dd')
}

/**
 * 格式化日期时间为 yyyy-MM-dd hh:mm:ss
 * @param {Date|string|number} date
 * @returns {string}
 */
export function formatDateTime(date) {
  return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
}

/**
 * 获取相对时间描述
 * @param {Date|string|number} date
 * @returns {string}
 */
export function getRelativeTime(date) {
  if (!date) return ''

  const d = date instanceof Date ? date : new Date(date)
  if (isNaN(d.getTime())) return ''

  const now = new Date()
  const diff = now.getTime() - d.getTime()
  const minute = 1000 * 60
  const hour = minute * 60
  const day = hour * 24
  const week = day * 7
  const month = day * 30

  if (diff < minute) {
    return '刚刚'
  } else if (diff < hour) {
    return Math.floor(diff / minute) + '分钟前'
  } else if (diff < day) {
    return Math.floor(diff / hour) + '小时前'
  } else if (diff < week) {
    return Math.floor(diff / day) + '天前'
  } else if (diff < month) {
    return Math.floor(diff / week) + '周前'
  } else {
    return formatDateOnly(d)
  }
}
