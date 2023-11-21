import { Icon, ListItem } from "@rneui/base";
import { useContext } from "react";
import { SectionList, Text } from "react-native";
import { ClassesContext } from "../util/redux";
import { useEffect } from "react";
import styles from "../common/styles";

function BookedClassesScreen() {
    const classes = useContext(ClassesContext);

    useEffect(() => {
        console.log('BookedClassesScreen', 'classes: ' + JSON.stringify(classes));
        console.log('BookedClassesScreen', 'classMap: ' + JSON.stringify(classMap));
    }, [classMap]);
    
    const classMap = classes
        .filter(c => c.booked)
        .reduce((r, a) => {
            r[`${a.classDay} ${a.classTime}`] = r[`${a.classDay} ${a.classTime}`] || [];
            r[`${a.classDay} ${a.classTime}`].push(a);
            return r;
        }, Object.create(null));

    return (
        <SectionList
            sections={
                Object.keys(classMap)
                    .map(key => ({ title: key, data: classMap[key] }))
            }
            renderItem={({item}) => 
            <ListItem>
                <Icon name='today' />
                <ListItem.Content>
                    <ListItem.Title>{item.teacher}</ListItem.Title>
                    <ListItem.Subtitle>{item.date}</ListItem.Subtitle>
                </ListItem.Content>
            </ListItem>
            }
            renderSectionHeader={({section}) => <Text style={styles.sectionHeader}>{section.title}</Text>}
        />
    );
}

export default BookedClassesScreen;
