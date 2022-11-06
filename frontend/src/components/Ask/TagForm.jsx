import { useEffect } from 'react';
import styled from 'styled-components';
import useInput from './hooks/useInput';
import useTag from './hooks/useTag';

const TagForm = ({ callback }) => {
	const [tags, setTags, handleRemoveTag] = useTag();
	const [value, setValue, onChangeValue] = useInput();

	const handleEditTag = (e) => {
		if (e.keyCode === 8 && value === '' && tags.length > 0) {
			const editTag = tags.pop();
			const newTags = [...tags];
			setTags(newTags);
			setValue(editTag + ' ');
		}
	};

	const handleAddTag = (e) => {
		if (e.key !== 'Enter' && e.key !== ' ') return;
		else {
			setValue(value.trim().toLowerCase());
		}
		if (!value.trim()) return;
		if (tags.includes(value) || tags.length > 4) {
			setValue('');
			return;
		} else {
			setTags([...tags, value]);
			setValue('');
		}
	};

	useEffect(() => {
		callback(tags);
	}, [tags]);

	return (
		<Style>
			<div className="container">
				{tags.map((tag, idx) => (
					<div className="tag" key={idx}>
						<span>{tag}</span>
						<button onClick={() => handleRemoveTag(idx)}>&times;</button>
					</div>
				))}
				<input
					type="text"
					placeholder="e.g. (objective-c json windows)"
					value={value}
					pattern="[a-zA-Z0-9]"
					onKeyDown={(e) => {
						handleAddTag(e);
						handleEditTag(e);
					}}
					onChange={onChangeValue}
				/>
			</div>
		</Style>
	);
};

const Style = styled.div`
	.container {
		input {
			display: inline-flex;
			flex-direction: row;
			flex-wrap: wrap;
			padding: 0 0.5rem 0 0.5rem;
			border: none;
			font-size: 12px;
			font-weight: 500;
			height: 1.6rem;
			width: 50%;
			&:focus {
				outline: none;
			}
		}
		input::placeholder {
			color: #bbbbbb;
		}
		font-size: 12px;
		border: 1px solid rgb(179, 183, 188);
		border-radius: 2px;
		padding: 0.3rem;
	}
	.tag {
		display: inline;
		background-color: rgb(227, 236, 243);
		padding: 0.35rem 0.25rem 0.35rem 0.25rem;
		border-radius: 4px;
		font-weight: 400;
		color: hsl(206, 100%, 40%);
		margin-right: 0.25rem;
	}
	.tag button {
		padding: 0 0.2rem;
		margin-left: 0.2rem;
		color: hsl(206, 100%, 40%);
		font-weight: 700;
		border-radius: 3px;
	}
	.tag button:hover {
		background-color: hsl(206, 100%, 40%);
		color: rgb(227, 236, 243);
	}
	.container:focus-within {
		outline: none;
		border-color: #9ecaed;
		box-shadow: 0 0 10px #9ecaed;
	}
`;

export default TagForm;
