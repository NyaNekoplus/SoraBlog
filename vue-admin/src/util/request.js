/**
 * @Author: Vincent Tsai
 * @Description:
 * @Date: create in 2021/2/7 13:36
 */
import axios from "axios";


const request = axios.create({
  baseURL: "/",
  timeout:20000
})

export default request;
