import { createAsyncThunk } from "@reduxjs/toolkit";
import { api } from "../../util/api.backend";
import { setToken } from "../slices/authSlice";

export const login = createAsyncThunk(
    'auth/login',
    async (credentials, {rejectWithValue, dispatch}) => {
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
    (arg, {rejectWithValue}) => console.log('hey')
)