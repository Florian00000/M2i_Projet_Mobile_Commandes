import { createAsyncThunk } from "@reduxjs/toolkit";

export const testTest = createAsyncThunk(
    "test/testTest",
    async (_, action) => {
        console.log('test')
        action.rejectWithValue('lol')
        // return 'test'
    }
)