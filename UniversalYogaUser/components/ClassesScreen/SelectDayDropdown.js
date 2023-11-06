import { useEffect, useState } from "react";
import { Text, View } from "react-native";
import Select from 'react-native-picker-select';

function SelectDayDropdown({ classes, setSelectedDay }) {
    const [items, setItems] = useState([]);

    useEffect(() => {
        const set = new Set();
        const items = classes
            .filter(c => {
                if (set.has(c.classDay)) return false;
                set.add(c.classDay);
                return true;
            })
            .map(c => ({label: c.classDay, value: c.classDay}));
        setItems(items);
    }, [classes]);

    return (
        <View style={{marginHorizontal: 8, marginTop: 8}}>
            <Text style={{fontWeight: 600}}>
                Filter by Day of Week:
            </Text>
            <Select
                onValueChange={setSelectedDay}
                items={items}
            />
        </View>
    );
}

export default SelectDayDropdown;
