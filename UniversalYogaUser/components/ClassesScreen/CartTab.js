import { Button, Icon } from "@rneui/base";
import { Text, View } from "react-native";

function CartTab({ cart }) {
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

            <Button title='Proceed' disabled={cart.length === 0} />
    </View>
}

export default CartTab;
