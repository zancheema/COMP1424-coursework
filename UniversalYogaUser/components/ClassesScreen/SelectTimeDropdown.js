import { useEffect, useState } from "react";
import { Text, View } from "react-native";
import Select from 'react-native-picker-select';

function SelectDayDropdown({ classes, setSelectedTime }) {    
    const [items, setItems] = useState([]);

    useEffect(() => {
        const set = new Set();
        const items = classes
            .filter(c => {
                if (set.has(c.classTime)) return false;
                set.add(c.classTime);
                return true;
            })
            .map(c => ({label: c.classTime, value: c.classTime}));
        setItems(items);
    }, [classes]);

    return (
        <View style={{marginHorizontal: 8, marginTop: 8}}>
            <Text style={{fontWeight: 600}}>
                Filter by Time of Day:
            </Text>
            <Select
                onValueChange={setSelectedTime}
                items={items}
            />
        </View>
    );
}

export default SelectDayDropdown;
