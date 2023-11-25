import { Button, Icon, ListItem } from "@rneui/base";
import { useContext } from "react";
import { FlatList, Text, View } from "react-native";
import { DispatchContext } from "../util/redux";
import { StackActions } from "@react-navigation/native";

function CartScreen({ route, navigation }) {
    const cart = route.params.cart;
    const clearCart = route.params.clearCart;
    const dispatch = useContext(DispatchContext);


    function bookAll() {
        for (var c of cart) {
            console.log('c: ' + JSON.stringify(c));
            dispatch({
                type: 'book',
                instanceId: c.instanceId
            });
        }
        clearCart();

        const popAction = StackActions.pop(1);
        navigation.dispatch(popAction);
    }

    return (
        <View style={{flex: 1}}>
            <FlatList data={cart} renderItem={({item}) => (
                <ListItem>
                    <Icon name='today' />
                    <ListItem.Title>{item.classDay}</ListItem.Title>
                    <ListItem.Subtitle>{item.classTime}</ListItem.Subtitle>
                    <ListItem.Content>
                        <ListItem.Title>{item.teacher}</ListItem.Title>
                        <ListItem.Subtitle>{item.date}</ListItem.Subtitle>
                    </ListItem.Content>
                </ListItem>
            )} />
            <Button onPress={bookAll} style={{marginHorizontal: 10, martinTop: 10, marginBottom: 20}}>Book All</Button>
        </View>
    );
}

export default CartScreen;
