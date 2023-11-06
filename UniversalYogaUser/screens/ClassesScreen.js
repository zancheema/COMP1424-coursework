import { ScrollView, SectionList, Text, View } from "react-native";
import { dummyClasses } from "../common/dummy";
import { useEffect, useState } from "react";
import ClassList from "../components/ClassesScreen/ClassList";
import SelectDayDropdown from "../components/ClassesScreen/SelectDayDropdown";
import SelectTimeDropdown from "../components/ClassesScreen/SelectTimeDropdown";
import CartTab from "../components/ClassesScreen/CartTab";

function ClassesScreen() {
    const [classes, setClasses] = useState(dummyClasses);
    const [selectedDay, setSelectedDay] = useState(null);
    const [selectedTime, setSelectedTime] = useState(null);

    function getClasses() {
        setClasses(dummyClasses);
    }

    useEffect(() => {
        getClasses();
    }, []);

    return (
        <View style={{flex: '1'}}>
            <SelectDayDropdown
                classes={classes} 
                setSelectedDay={setSelectedDay} 
            />
            <SelectTimeDropdown
                classes={classes} 
                setSelectedTime={setSelectedTime}
            />
            <ClassList classes={classes} selectedDay={selectedDay} selectedTime={selectedTime} />
            <CartTab />
        </View>
    );
}

export default ClassesScreen;
