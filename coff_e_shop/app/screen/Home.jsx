import { StyleSheet, Text, View } from 'react-native'
import React, { useEffect } from 'react'
import { useDispatch } from 'react-redux'
import { login, testApi } from '../store/services/authService'
import { api } from '../util/api.backend'

const Home = () => {

  const dispatch = useDispatch()

  useEffect(() => {
    console.log(api.get('/test/not-authenticated'))
    dispatch(login())
  }, [])
  

  return (
    <View>
      <Text>Home</Text>
    </View>
  )
}

export default Home

const styles = StyleSheet.create({
    
})