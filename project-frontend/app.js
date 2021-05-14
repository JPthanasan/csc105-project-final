import 'regenerator-runtime/runtime';
import axios from 'axios';



const getTodoItems = async () => {
  try {
    const response = await axios.get("https://jsonplaceholder.typicode.com/todos/1");

    const todoItems = response.data;

    console.log(`GET: Here's the list of todos`, todoItems);

    return todoItems;
  } catch (errors) {
    console.error(errors);
  }
};
getTodoItems();