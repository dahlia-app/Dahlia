package xyz.wingio.dahlia.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.InsertDriveFile
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import xyz.wingio.dahlia.R
import xyz.wingio.dahlia.domain.models.Project
import xyz.wingio.dahlia.utils.formatDate
import xyz.wingio.dahlia.utils.relativeTime
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProjectItem(
    project: Project,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Outlined.InsertDriveFile,
                contentDescription = null,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = project.config.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = pluralStringResource(R.plurals.plural_request, count = project.requests.size, project.requests.size),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = relativeTime(project.lastModified),
                style = MaterialTheme.typography.labelSmall,
                color = LocalContentColor.current.copy(0.5f)
            )
        }
    }

}