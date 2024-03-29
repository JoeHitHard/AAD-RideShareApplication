import './Conversations.css';
import Conversation from './Conversation';
import { useEffect, useState } from 'react';
import axios from 'axios';

const Conversations = ({setChatID}) => {
    const [data, setData] = useState([]);
    useEffect(() => {
        const fetchData = async () => {
          
          try {
            const response = await axios.get('http://localhost:8082/api/rs/chat/conversations', {
              headers: {
                'Authorization': localStorage.getItem("jwtToken") || "XXX"
              }
            });
            setData(response.data);
          } catch (error) {
            if (error.response.status === 401) {
                localStorage.removeItem("jwtToken");
              }
            console.error('Error fetching conversations:', error);
          }
        };
    
        fetchData();
        const intervalId = setInterval(() => {
          fetchData();
        }, 5000);
      
        // Clean up interval on component unmount
        return () => clearInterval(intervalId);
      }, []);
    return (
		<div className="conversations-container">
			{data.map((chatId) => (
                <Conversation chat={chatId} currentUser={localStorage.getItem("currentUser")} setChatID={setChatID}/>
            ))}
		</div>
	);
}

export default Conversations;

