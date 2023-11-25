import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, TouchableOpacity, View } from 'react-native';
import ClassesScreen from './screens/ClassesScreen';
import CartScreen from './screens/CartScreen';
import { useReducer } from 'react';
import { ClassesContext, DispatchContext, classesReducer } from './util/redux';
import { dummyClasses } from './common/dummy';
import BookedClassesScreen from './screens/BookedClassesScreen';
import { Button, Icon } from '@rneui/base';
// import { TouchableOpacity } from '@gorhom/bottom-sheet';

const Stack = createNativeStackNavigator();

export default function App() {
  const [classes, dispatch] = useReducer(classesReducer, dummyClasses);

  return (
    <ClassesContext.Provider value={classes}>
      <DispatchContext.Provider value={dispatch}>
        <NavigationContainer>
          <Stack.Navigator>
            <>
            <Stack.Screen name='Classes' component={ClassesScreen} options={({navigation, route}) => ({
              headerRight: () => (
                <TouchableOpacity onPress={() => navigation.navigate('Bookings')}>
                  <Icon name='bookmark' />
                </TouchableOpacity>
              )
            })} />
            <Stack.Screen name='Cart' component={CartScreen} />
            <Stack.Screen name='Bookings' component={BookedClassesScreen} />
            </>
          </Stack.Navigator>
        </NavigationContainer>
      </DispatchContext.Provider>
    </ClassesContext.Provider>
  )
};
