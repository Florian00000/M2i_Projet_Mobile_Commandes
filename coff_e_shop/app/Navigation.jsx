import React from 'react'
import { NavigationContainer } from '@react-navigation/native'
import { createBottomTabNavigator } from '@react-navigation/bottom-tabs'
import Icon from 'react-native-vector-icons/MaterialIcons'
import Home from './screen/Home'


const Tab = createBottomTabNavigator()

const Navigation = () => {

    return (
        <NavigationContainer>
            <Tab.Navigator
                initialRouteName='home'
                screenOptions={{
                    headerShown: false,
                    tabBarShowLabel: false,
                    tabBarStyle: {height: 70},
                }}
            >
                <Tab.Screen name="home" component={Home} options={{
                    tabBarLabel:'Accueil',
                    tabBarIcon: () => <Icon name="home" size={45}/>
                }}/>
            </Tab.Navigator>
        </NavigationContainer>
    )
}

export default Navigation