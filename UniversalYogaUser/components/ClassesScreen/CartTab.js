import { Button, Icon } from "@rneui/base";
import { Text, View } from "react-native";

function CartTab({ cart, navigation }) {
    
    function openCart() {
        navigation.navigate('Cart', { cart });
    }

    return <View 
        style={{
            backgroundColor: 'yellow', 
            flexDirection: 'row', 
            justifyContent: 'space-between',
            padding: 20,
        }}>
            <View
                style={{flexDirection: 'row'}}
            >
                <Icon name="shopping-cart" />
                <Text style={{color: 'black', marginEnd: 10, fontSize: 15, fontWeight: 600}}>{cart.length}</Text>
            </View>

            <Button title='Proceed' disabled={cart.length === 0} onPress={openCart} />
    </View>
}

export default CartTab;
