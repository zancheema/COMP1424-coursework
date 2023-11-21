import { ScrollView, SectionList, Text, View } from "react-native";
import { dummyClasses } from "../common/dummy";
import { useEffect, useState } from "react";
import ClassList from "../components/ClassesScreen/ClassList";
import SelectDayDropdown from "../components/ClassesScreen/SelectDayDropdown";
import SelectTimeDropdown from "../components/ClassesScreen/SelectTimeDropdown";
import CartTab from "../components/ClassesScreen/CartTab";
import { useCart } from "../util/hooks";
import { useContext } from "react";
import { ClassesContext } from "../util/redux";

function ClassesScreen({ navigation }) {
    // const [classes, setClasses] = useState(dummyClasses);
    const classes = useContext(ClassesContext).filter(c => !c.booked);

    const [selectedDay, setSelectedDay] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);

    const { cart, addToCart, removeFromCart, clearCart } = useCart();

    function getClasses() {
        // setClasses(dummyClasses);
        console.log('classes: ' + JSON.stringify(classes));
    }

    useEffect(() => {
        getClasses();
    }, []);

    useEffect(() => {
        console.log('cart', cart);
    }, [cart]);

    return (
        <View style={{flex: 1}}>
            <SelectDayDropdown
                classes={classes} 
                setSelectedDay={setSelectedDay} 
            />
            <SelectTimeDropdown
                classes={classes} 
                setSelectedTime={setSelectedTime}
            />
            <ClassList 
                classes={classes} 
                selectedDay={selectedDay} 
                selectedTime={selectedTime} 
                cart={cart}
                addToCart={addToCart} 
                removeFromCart={removeFromCart} 
            />
            <CartTab cart={cart} clearCart={clearCart} navigation={navigation} />
        </View>
    );
}

export default ClassesScreen;
