import { ScrollView, SectionList, Text, View } from "react-native";
import { dummyClasses } from "../common/dummy";
import { useEffect, useState } from "react";
import ClassList from "../components/ClassesScreen/ClassList";
import SelectDayDropdown from "../components/ClassesScreen/SelectDayDropdown";
import SelectTimeDropdown from "../components/ClassesScreen/SelectTimeDropdown";
import CartTab from "../components/ClassesScreen/CartTab";
import { useCart } from "../util/hooks";
import { useContext } from "react";
import { ClassesContext, DispatchContext } from "../util/redux";
import { getClassInstances } from "../api/api";

function ClassesScreen({ navigation }) {
    // const [classes, setClasses] = useState(dummyClasses);
    const classes = useContext(ClassesContext).filter(c => !c.booked);
    const dispatch = useContext(DispatchContext);


    const [selectedDay, setSelectedDay] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);

    const { cart, addToCart, removeFromCart, clearCart } = useCart();

    async function getClasses() {
        const classInstances = await getClassInstances();
        console.log('getClasses: ' + JSON.stringify(classInstances));
        dispatch({ type: 'replace', data: classInstances });
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
