import { StyleSheet, Text, View } from 'react-native'
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { increment } from '../store/slices/testSlice'
import { testTest } from '../store/services/testService'
import { testApi } from '../store/services/authService'
// import { login, testApi } from '../store/services/authService'
// import { api } from '../util/api.backend'

const Home = () => {

  const dispatch = useDispatch()
  const {value} = useSelector(state => state.test)

  useEffect(() => {
    dispatch(testApi())
    dispatch(increment())
    dispatch(testTest())
    console.log(value)

    // console.log(api.get('/test/not-authenticated').then(e => e))
  }, [])
  console.log(value)

  

  return (
    <View>
      <Text>Home</Text>
    </View>
  )
}

export default Home

const styles = StyleSheet.create({
    
})