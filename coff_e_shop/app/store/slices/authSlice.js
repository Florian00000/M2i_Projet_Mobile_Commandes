import { createSlice } from "@reduxjs/toolkit";
import { testApi } from "../services/authService";

const authSlice = createSlice({
    name: 'auth',
    initialState: {
        token: null,
        isLoading: false,
        error: null,
    },
    reducers: {
        setToken: (state, action) => {
            state.token = action.payload
        },
    },
    extraReducers: (builder) => {
        builder
            // .addCase(testApi.rejected, (state, action) => console.log(action.error))
            .addMatcher(({type}) => (type.endsWith('/fulfilled') && type.startsWith('auth')), (state, action) => {
                console.log(action.type)
                state.isLoading = false
                state.error = null
            })
            .addMatcher(({type}) => (type.endsWith('/pending') && type.startsWith('auth')), (state, action) => {
                console.log(action.type)
                state.isLoading = true
            })
            .addMatcher(({type}) => (type.endsWith('/rejected') && type.startsWith('auth')), (state, {type, error}) => {
                console.log(type)
                console.log(error.message)
                state.isLoading = false
                state.error = action.error.message
            })
    }
})


export const {
    setToken,
} = authSlice.actions

export default authSlice.reducer