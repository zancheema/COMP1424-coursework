import { Button, Icon, ListItem } from "@rneui/base";
import { SectionList, Text } from "react-native";
import styles from "../../common/styles";
import { useEffect } from "react";

function ClassList({ classes, selectedDay, selectedTime, cart, addToCart, removeFromCart }) {
    const classMap = classes.reduce((r, a) => {
        r[`${a.classDay} ${a.classTime}`] = r[`${a.classDay} ${a.classTime}`] || [];
        r[`${a.classDay} ${a.classTime}`].push(a);
        return r;
    }, Object.create(null));

    return (
        <> 
        <SectionList
            sections={
                Object.keys(classMap)
                    .map(key => ({ title: key, data: classMap[key] }))
                    .filter(item => !item.booked)
                    .filter(item => (!selectedDay || item.title.includes(selectedDay)) && (!selectedTime || item.title.includes(selectedTime)))
            }
            renderItem={({item}) => 
            <ListItem.Swipeable 
                rightContent={(reset) => (
                    cart.includes(item)
                    ? <Button 
                        title='Remove'
                        onPress={() => {
                            // implement booking functionality
                            removeFromCart(item);
                            reset();
                        }}
                        icon={{name: 'remove-shopping-cart', color: 'white'}}
                        buttonStyle={{minHeight: '100%', backgroundColor: 'red'}} />
                    : <Button
                        title='Add'
                        onPress={() => {
                            // implement booking functionality
                            addToCart(item);
                            reset();
                        }}
                        icon={{name: 'add-shopping-cart', color: 'white'}}
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
