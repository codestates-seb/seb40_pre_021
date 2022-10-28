import styled from 'styled-components';
import { AiOutlineSearch } from 'react-icons/ai';

const SearchStyle = styled.li`
	width: 800px;
	display: flex;
	justify-content: center;
	align-items: center;
`;
const SearchBox = styled.div`
	border: 1px solid rgb(179, 183, 188);
	height: 65%;
	width: 95%;
	border-radius: 3px;
	background-color: white;
	display: flex;
	align-items: center;
	justify-content: center;

	&:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
	input {
		width: 95%;
		border: none;
		font-size: 13px;
		font-weight: bold;

		&:focus {
			outline: none;
		}
	}
`;
const Search = () => {
	return (
		<SearchStyle>
			<SearchBox>
				<AiOutlineSearch size="24" color="gray" />
				<input placeholder="Search..." />
			</SearchBox>
		</SearchStyle>
	);
};

export default Search;
