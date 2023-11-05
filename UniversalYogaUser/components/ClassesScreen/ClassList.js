import { Button, Icon, ListItem } from "@rneui/base";
import { SectionList, Text } from "react-native";
import styles from "../../common/styles";

function ClassList({ classes, selectedDay, selectedTime }) {
    const classItems = classes.filter(c => (!selectedDay || c.dayOfWeek === selectedDay) && (!selectedTime || c.timeOfDay === selectedTime));

    return (
        <>
            <SectionList
                sections={
                    classItems.map(c => ({ title: `${c.dayOfWeek} ${c.timeOfDay}`, data: c.classList }))
                }
                renderItem={({item}) => 
                <ListItem.Swipeable 
                    rightContent={(reset) => (
                        <Button
                            title='Book'
                            onPress={() => {
                                // implement booking functionality
                                reset();
                            }}
                            icon={{name: 'book', color: 'white'}}
                            buttonStyle={{minHeight: '100%', backgroundColor: 'green'}} />
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
