import { Text, View } from "react-native";
import Select from 'react-native-picker-select';

function SelectDayDropdown({ classes, setSelectedDay }) {
    const items = classes.map(c => ({label: c.dayOfWeek, value: c.dayOfWeek}));

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
