import { Text, View } from "react-native";
import Select from 'react-native-picker-select';

function SelectDayDropdown({ classes, setSelectedTime }) {
    const items = classes.map(c => ({label: c.timeOfDay, value: c.timeOfDay}));

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
