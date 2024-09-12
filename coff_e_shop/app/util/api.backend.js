import axios from "axios";

const defaultInterceptor = [
    (response) => {
        console.log(response.data)
        return response.data
    },
    (error) => {
        console.warn(e)
        return Promise.reject(error)
    }
]

export const api = axios.create({baseURL: "http://10.0.2.2:8080/api"})
api.interceptors.response.use(...defaultInterceptor)