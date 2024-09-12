import React from 'react'
import Navigation from './Navigation'
import { Provider } from 'react-redux'
import store from './store/store'

const App = () => {
  return (
    <React.Fragment>
      <Provider store={store}>
        <Navigation />
      </Provider>
    </React.Fragment>
  )
}

export default App