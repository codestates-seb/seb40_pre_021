import styled from 'styled-components';
import ListBox from '../ListBox';
import SortButtonGroup from '../SortButtonGroup';
import Title from '../Title';
import TagsList from './TagsList';

let sortData = [
	{
		id: 0,
		name: 'Score',
		clicked: true,
	},
	{
		id: 1,
		name: 'Name',
		clicked: false,
	},
];

const Tags = ({
	tag,
	setTag,
	limit,
	handleTabChange,
	handleSortLists,
	flex = false,
}) => {
	return (
		<Container>
			<TitleBox>
				<Title
					title="Tags"
					number={tag?.length}
					flex={flex}
					handleTabChange={handleTabChange}
				/>
				{!flex ? (
					<SortButtonGroup
						menus={sortData}
						handleSortLists={handleSortLists}
						data={tag}
						callback={setTag}
						tag={true}
					/>
				) : null}
			</TitleBox>
			<ListBox
				text="You have not participated in any tags"
				lists={tag}
				component={<TagsList lists={tag} limit={limit} />}
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
		width: 100%;
	}
`;

const TitleBox = styled.div`
	display: flex;
	margin-bottom: 8px;
	align-items: flex-end;
	justify-content: space-between;
`;
