import styled from 'styled-components';
import ListBox from '../ListBox';
import Title from '../Title';
import TagsList from './TagsList';

const Tags = ({ tag }) => {
	return (
		<Container>
			<TitleBox>
				<Title title="Tags" number={tag?.length} flex={true} />
			</TitleBox>
			<ListBox
				text="You have not participated in any tags"
				lists={tag}
				component={<TagsList lists={tag} />}
			/>
		</Container>
	);
};

export default Tags;

const Container = styled.div`
	&&& {
		height: 100%;
		display: flex;
		flex-direction: column;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	justify-content: space-between;
`;
