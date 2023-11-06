import { ScrollView, SectionList, Text, View } from "react-native";
import { dummyClasses } from "../common/dummy";
import { useEffect, useState } from "react";
import ClassList from "../components/ClassesScreen/ClassList";
import SelectDayDropdown from "../components/ClassesScreen/SelectDayDropdown";
import SelectTimeDropdown from "../components/ClassesScreen/SelectTimeDropdown";
import CartTab from "../components/ClassesScreen/CartTab";
import { useCart } from "../util/hooks";

function ClassesScreen() {
    const [classes, setClasses] = useState(dummyClasses);
    const [selectedDay, setSelectedDay] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);

    const { cart, addToCart, removeFromCart } = useCart();

    function getClasses() {
        setClasses(dummyClasses);
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
            <CartTab cart={cart} />
        </View>
    );
}

export default ClassesScreen;
