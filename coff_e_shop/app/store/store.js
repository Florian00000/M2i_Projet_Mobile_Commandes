import { configureStore } from "@reduxjs/toolkit";
import authSlice from "./slices/authSlice";
import testSlice from "./slices/testSlice";

export default configureStore({
    reducer: {
        auth: authSlice,
        test: testSlice
    },
    middleware: (getDefaultMiddleware) => getDefaultMiddleware({
        // serializableCheck: false,
        // immutableCheck: false
    })
})