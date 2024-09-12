import { createSlice } from "@reduxjs/toolkit";
import { login } from "../services/authService";

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
    extraReducers: ({addMatcher, addCase}) => {

        addCase(login.fulfilled, (state, action) => {
            console.log(action.payload)
            
        })
        addCase(login.rejected, (state, action) => {
            console.log(action.payload)
            console.warn(action.error)
        })

        addMatcher(({type}) => (type.endsWith('/fulfilled') && type.startsWith('auth')), (state, action) => {
            console.log(action.type)
            state.isLoading = false
            state.error = null
        })
        addMatcher(({type}) => (type.endsWith('/pending') && type.startsWith('auth')), (state) => {
            console.log(action.type)
            state.isLoading = true
        })
        addMatcher(({type}) => (type.endsWith('/rejected') && type.startsWith('auth')), (state, quetsche) => {
            console.log(quetsche.type)
            console.warn("", quetsche.error)
            // state.isLoading = false
            // state.error = action.error
        })
    }
})


export const {
    setToken,
} = authSlice.actions

export default authSlice.reducer