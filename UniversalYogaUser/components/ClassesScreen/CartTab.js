import { Icon } from "@rneui/base";
import { View } from "react-native";

function CartTab() {
    return <View 
        style={{
            // height: 30, 
            // height: '10%',
            maxHeight: 70,
            backgroundColor: 'yellow', 
            flex: 1, 
            flexDirection: 'row', 
            justifyContent: 'flex-end',
            padding: 10
        }}>
        <Icon name="shopping-cart" />
    </View>
}

export default CartTab;
