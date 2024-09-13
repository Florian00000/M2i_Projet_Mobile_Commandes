import axios from "axios";

const defaultInterceptor = [
    (response) => {
        console.log(response.data)
        return response.data
    },
    (error) => {
        console.warn(error)
        return Promise.reject(error)
    }
]

const api = axios.create({baseURL: "http://10.0.2.2:8080/api", setTimeout: 1500})
api.interceptors.response.use(...defaultInterceptor)
export default api