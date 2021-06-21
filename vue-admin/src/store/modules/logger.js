/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/4/12 21:45
 */
import { make } from 'vuex-pathify'
import {getInfo, login, logout} from "../../api/login";
import {getToken, removeToken, setToken} from "../../util/cookie";
import {error} from "../../util/routes";

const state = {
  token: getToken(),
  username: '',
  avatar: '',
  proxy: null,
}

const mutations = make.mutations(state)

const actions = {
  Login({commit},userInfo){
    const username = userInfo.username.trim()
    const password = userInfo.password.trim()
    const rememberMe = userInfo.rememberMe
    return new Promise((resolve, reject) => {
      let params = new URLSearchParams()
      params.append('username', username)
      params.append('password', password)
      params.append('rememberMe', rememberMe)
      login(params).then(response => {

          const data = response.data
          // 向cookie中设置token
          setToken(data)
          // 向store中设置cookie
          commit('token', data)

        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },
  GetInfo({commit, state}){
    return new Promise((resolve, reject) => {
      getInfo().then(response => {
        const user = response.data
        if (user.userProxy!=null){
          commit('proxy',user.userProxy)
        }else {
          reject('用户权限不足')
        }
        commit('username',user.username)
        commit('avatar',user.avatarUrl)
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },
  Logout({commit, state}){
    return new Promise((resolve, reject) => {
      logout(state.token).then(response => {
        commit('token', '')
        commit('proxy', null)
        removeToken()
        resolve(response)
      }).catch(error => {
        reject(error)
      })
    })
  },
  FrontEndLogout({ commit }) {
    return new Promise(resolve => {
      commit('token', '')
      removeToken()
      resolve()
    })
  }
}

const getters = {
  ...make.getters(state),
  token: state => {
    return state.token
  }
}


export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters,
}
