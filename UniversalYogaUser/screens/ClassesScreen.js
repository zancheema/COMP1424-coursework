import { SectionList, Text, View } from "react-native";
import { dummyClasses } from "../common/dummy";
import { useEffect, useState } from "react";
import styles from "../common/styles";
import { Button, Icon, ListItem } from "@rneui/base";

function ClassesScreen() {
    const [classes, setClasses] = useState(dummyClasses);

    function getClasses() {
        setClasses(dummyClasses);
    }

    useEffect(() => {
        getClasses();
    }, []);

    return (
        <View>
            <SectionList 
                sections={
                    classes.map(c => ({ title: `${c.dayOfWeek} ${c.timeOfDay}`, data: c.classList }))
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
        </View>
    );
}

export default ClassesScreen;
