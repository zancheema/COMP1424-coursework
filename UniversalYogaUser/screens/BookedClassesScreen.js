import { Icon, ListItem } from "@rneui/base";
import { SectionList } from "react-native";

function BookedClassesScreen() {
    
    const classMap = classes.reduce((r, a) => {
        r[`${a.classDay} ${a.classTime}`] = r[`${a.classDay} ${a.classTime}`] || [];
        r[`${a.classDay} ${a.classTime}`].push(a);
        return r;
    }, Object.create(null));

    return (
        <SectionList
            sections={
                Object.keys(classMap)
                    .map(key => ({ title: key, data: classMap[key] }))
                    .filter(item => item.booked)
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
