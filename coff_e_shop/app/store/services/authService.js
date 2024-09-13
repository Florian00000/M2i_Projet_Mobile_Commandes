import { createAsyncThunk } from "@reduxjs/toolkit";
import api from "../../util/api.backend";
import { setToken } from "../slices/authSlice";
import { increment } from "../slices/testSlice";

export const login = createAsyncThunk(
    'auth/login',
    async (credentials, {rejectWithValue, dispatch}) => {
        console.log('coucou!')
        try {
            const token = await api.post('/auth/login', {email: 'gabitbol@mail.fr', password: '123456'})
            console.log(token)
            dispatch(setToken(token))
        } catch (e) {
            console.warn(e)
            rejectWithValue(e)
        }
    }
)

export const register = createAsyncThunk(
    'auth/register',
    async (credentials, {rejectWithValue, dispatch}) => {
        try {
            await api.post('/auth/register', credentials)
        } catch (e) {
            console.warn(e)
            rejectWithValue(e)
        }
    }
)

export const testApi = createAsyncThunk(
    'auth/testApi',
    async (arg, action) => {
        console.log("hey!!!!")
        action.dispatch(increment())
        action.dispatch(increment())
        try {
            console.log('on try')
            const response = await api.get('/test/not-authenticated')
            if (!response) throw Error('timeout')
            // console.log(response)
        } catch (error) {
            console.warn(error)
            action.rejectWithValue(error)
        }
    }
)