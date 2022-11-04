import styled from 'styled-components';
import { AiOutlineSearch } from 'react-icons/ai';
import { useSelector } from 'react-redux';
import { selectTags } from '../../modules/tagsReducer';
import { useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
const boxShadow = '0 4px 6px rgb(32 33 36 / 28%)';
const DropDownContainer = styled.ul`
	position: absolute;
	max-width: 90%;
	min-height: ${(props) => props.height * 26}px;
	top: 48px;
	background-color: #ffffff;
	display: flex;
	flex-direction: column;
	border: 1px solid rgb(223, 225, 229);
	/* border-radius: 1rem; */
	box-shadow: ${boxShadow};
	z-index: 3;

	.focus {
		background-color: #ffffff;
		filter: brightness(90%);
	}
	> li {
		padding: 0.3rem 1rem;
		&:hover {
			background-color: #ffffff;
			filter: brightness(90%);
		}
	}
`;
const SearchStyle = styled.li`
	position: relative;
	flex-grow: 1;
	max-width: 50%;
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
	const [inputValue, setInputValue] = useState('');
	const tags = useSelector(selectTags);
	const [options, setOptions] = useState([]);
	const [index, setIndex] = useState(0);

	const inputRef = useRef();
	const ulRef = useRef({});

	const navigate = useNavigate();

	const keyEvent = (e) => {
		if (options.length > 0) {
			switch (e.key) {
				case 'Enter':
					ulRef.current.children[index].click();
					break;
				case 'ArrowDown':
					setIndex(index + 1);
					if (ulRef.current.childElementCount === index + 1) setIndex(0);
					break;
				case 'ArrowUp':
					setIndex(index - 1);
					if (index <= 0) {
						setIndex(0);
					}
					break;
				case 'Escape':
					setOptions([]);
					setIndex(0);
					break;
			}
		}
		if (options.length === 0) {
			if (e.key === 'Enter') {
				if (inputValue !== undefined) {
					let searchWords = inputValue.split(' ').filter((ele) => {
						return ele !== '';
					});
					tags.map((tag) => {
						searchWords.map((word, idx) => {
							if (tag === word) {
								searchWords[idx] = `[${word}]`;
							}
						});
					});
					const q = searchWords.join(' ');
					navigate(`/search/${q}`);
					setInputValue('');
				}
			}
		}
	};

	const handleDropDownClick = (clickedOption) => {
		const text = inputValue.split(' ');
		text[text.length - 1] = clickedOption;
		setInputValue(text.join(' '));
		setIndex(0);
		setOptions([]);
		inputRef.current.focus();
	};

	const NewOptions = (text) => {
		const newOptions = [...tags];
		setOptions(
			newOptions.filter((ele) => {
				if (text !== '') {
					return ele.includes(text);
				} else {
					setIndex(0);
				}
			}),
		);
	};
	const handleInputChange = (event) => {
		const text = event.target.value;

		setInputValue(text);

		NewOptions(text.split(' ').at(-1));
	};
	const DropDown = () => {
		const height = options.length;
		return (
			<DropDownContainer height={height} ref={ulRef}>
				{options.map((ele, idx) => {
					return (
						<li
							key={idx}
							className={index === idx ? 'focus' : ''}
							role="presentation"
							onClick={() => handleDropDownClick(ele)}>
							{ele}
						</li>
					);
				})}
			</DropDownContainer>
		);
	};
	return (
		<SearchStyle>
			<SearchBox>
				<AiOutlineSearch size="24" color="gray" />
				<input
					ref={inputRef}
					placeholder="Search..."
					onKeyUp={keyEvent}
					onChange={handleInputChange}
					value={inputValue}
				/>
				{options.length > 0 && <DropDown />}
			</SearchBox>
		</SearchStyle>
	);
};

export default Search;
