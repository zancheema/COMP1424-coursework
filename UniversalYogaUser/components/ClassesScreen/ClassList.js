import { Button, Icon, ListItem } from "@rneui/base";
import { SectionList, Text } from "react-native";
import styles from "../../common/styles";
import { useEffect } from "react";

function ClassList({ classes, selectedDay, selectedTime }) {
    const classMap = classes.reduce((r, a) => {
        r[`${a.classDay} ${a.classTime}`] = r[`${a.classDay} ${a.classTime}`] || [];
        r[`${a.classDay} ${a.classTime}`].push(a);
        return r;
    }, Object.create(null));

    useEffect(() => {
        console.log('classMap: ', JSON.stringify(classMap));
    }, [classMap]);
    // const classItems = classMap.filter(c => (!selectedDay || c.classDay === selectedDay) && (!selectedTime || c.classTime === selectedTime));

    // const [selectedItems, setSelectedItems]

    return (
        <> 
        <SectionList
            sections={
                Object.keys(classMap)
                    .map(key => ({ title: key, data: classMap[key] }))
                    .filter(item => (!selectedDay || item.title.includes(selectedDay)) && (!selectedTime || item.title.includes(selectedTime)))
            }
            renderItem={({item}) => 
            <ListItem.Swipeable 
                rightContent={(reset) => (
                    <Button
                        title={<Text style={{color: 'black', fontWeight: 600, fontSize: 15}}>Add</Text>}
                        onPress={() => {
                            // implement booking functionality
                            reset();
                        }}
                        icon={{name: 'add-shopping-cart', color: 'black'}}
                        buttonStyle={{minHeight: '100%', backgroundColor: 'yellow'}} />
                )}
            >
                <Icon name='today' />
                <ListItem.Content>
                    <ListItem.Title>{item.teacher}</ListItem.Title>
                    <ListItem.Subtitle>{item.date}</ListItem.Subtitle>
                </ListItem.Content>
            </ListItem.Swipeable>
            }
            renderSectionHeader={({section}) => <Text style={styles.sectionHeader}>{section.title}</Text>}
        />
        </>
    );
}

export default ClassList;
