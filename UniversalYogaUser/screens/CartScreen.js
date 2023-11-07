import { Button, Icon, ListItem } from "@rneui/base";
import { FlatList, Text, View } from "react-native";

function CartScreen({ route }) {
    const cart = route.params.cart;

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
            <Button style={{marginHorizontal: 10, martinTop: 10, marginBottom: 20}}>Book All</Button>
        </View>
    );
}

export default CartScreen;
